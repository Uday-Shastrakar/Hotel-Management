import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { hotelService, roomService, bookingService } from '../services/api';
import RoomCard from '../components/RoomCard';
import { CircularProgress } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

const HotelDetails: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const queryClient = useQueryClient();

    const { data: hotel, isLoading: isHotelLoading } = useQuery({
        queryKey: ['hotel', id],
        queryFn: () => hotelService.getHotel(id!).then(res => res.data),
        enabled: !!id,
    });

    const { data: rooms, isLoading: isRoomsLoading } = useQuery({
        queryKey: ['rooms', id],
        queryFn: () => roomService.getRooms(id!).then(res => res.data),
        enabled: !!id,
    });

    const bookingMutation = useMutation({
        mutationFn: (roomId: number) => {
            // Mocking a 1-day booking from today to tomorrow for demo purposes
            const today = new Date().toISOString().split('T')[0];
            const tomorrow = new Date(Date.now() + 86400000).toISOString().split('T')[0];
            return bookingService.createBooking(1, roomId, today, tomorrow);
        },
        onSuccess: () => {
            alert('Booking Successful!');
            queryClient.invalidateQueries({ queryKey: ['rooms', id] });
            navigate('/my-bookings');
        },
        onError: (error: any) => {
            alert(error.response?.data?.message || 'Booking failed. Please try again.');
        }
    });

    if (isHotelLoading || isRoomsLoading) {
        return (
            <div className="flex items-center justify-center" style={{ minHeight: '400px' }}>
                <CircularProgress style={{ color: 'var(--primary)' }} />
            </div>
        );
    }

    if (!hotel) return <div>Hotel not found</div>;

    return (
        <div style={{ padding: '2rem 0' }}>
            <button 
                onClick={() => navigate(-1)} 
                className="flex items-center gap-2" 
                style={{ color: 'var(--primary)', background: 'none', marginBottom: '2rem', fontWeight: 600 }}
            >
                <ArrowBackIcon fontSize="small" /> Back to search
            </button>

            <div className="flex gap-8 items-start">
                <div style={{ flex: 1 }}>
                    <h1 style={{ fontSize: '2.5rem', marginBottom: '1rem' }}>{hotel.name}</h1>
                    <p style={{ fontSize: '1.1rem', color: 'var(--text-muted)', marginBottom: '2rem' }}>
                        {hotel.description}
                    </p>
                    
                    <h3 style={{ fontSize: '1.5rem', marginBottom: '1.5rem' }}>Select a Room</h3>
                    <div className="flex flex-col gap-4">
                        {rooms?.map((room: any) => (
                            <RoomCard 
                                key={room.id} 
                                room={room} 
                                onBook={(roomId) => bookingMutation.mutate(roomId)}
                                isBooking={bookingMutation.isPending && bookingMutation.variables === room.id}
                            />
                        ))}
                    </div>
                </div>

                <div className="glass-panel" style={{ width: '350px', padding: '2rem', borderRadius: '1.5rem', position: 'sticky', top: '100px' }}>
                    <h4 style={{ marginBottom: '1rem' }}>Hotel Info</h4>
                    <div style={{ fontSize: '0.9rem', color: 'var(--text-muted)' }}>
                        <p style={{ marginBottom: '0.5rem' }}><strong>City:</strong> {hotel.city}</p>
                        <p style={{ marginBottom: '0.5rem' }}><strong>Rating:</strong> {hotel.rating} / 5</p>
                        <p><strong>Amenities:</strong> WiFi, Pool, Gym, Spa</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default HotelDetails;
