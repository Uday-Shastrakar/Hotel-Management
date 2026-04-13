import { useQuery } from '@tanstack/react-query';
import { bookingService } from '../services/api';
import { CircularProgress } from '@mui/material';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

const MyBookings: React.FC = () => {
    const userId = 1; // Simulation
    const { data: bookings, isLoading } = useQuery({
        queryKey: ['my-bookings', userId],
        queryFn: () => bookingService.getUserBookings(userId).then(res => res.data),
    });

    return (
        <div style={{ padding: '2rem 0' }}>
            <h1 style={{ marginBottom: '2rem' }}>My <span className="gradient-text">Bookings</span></h1>

            {isLoading && (
                <div className="flex items-center justify-center" style={{ minHeight: '300px' }}>
                    <CircularProgress style={{ color: 'var(--primary)' }} />
                </div>
            )}

            {!isLoading && bookings?.length === 0 && (
                <div className="card" style={{ textAlign: 'center', padding: '4rem' }}>
                    <p style={{ color: 'var(--text-muted)', fontSize: '1.1rem' }}>You haven't made any bookings yet.</p>
                </div>
            )}

            {!isLoading && bookings && (
                <div className="flex flex-col gap-4">
                    {bookings.map((booking: any) => (
                        <div key={booking.id} className="card glass-panel flex items-center justify-between">
                            <div className="flex items-center gap-4">
                                <div style={{ color: 'var(--primary)' }}>
                                    <CheckCircleIcon fontSize="large" />
                                </div>
                                <div>
                                    <h4 style={{ fontSize: '1.2rem' }}>{booking.room.hotel.name}</h4>
                                    <p style={{ color: 'var(--text-muted)' }}>Room {booking.room.roomNumber} - {booking.room.type}</p>
                                </div>
                            </div>
                            
                            <div className="flex items-center gap-8">
                                <div className="flex items-center gap-2" style={{ color: 'var(--text-muted)' }}>
                                    <CalendarMonthIcon fontSize="small" />
                                    <span>{booking.startDate} to {booking.endDate}</span>
                                </div>
                                <div style={{ 
                                    background: 'var(--accent-bg)', 
                                    color: 'var(--accent)', 
                                    padding: '0.4rem 1rem', 
                                    borderRadius: '1rem',
                                    fontWeight: 600,
                                    fontSize: '0.9rem'
                                }}>
                                    {booking.status}
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default MyBookings;
