import KingBedIcon from '@mui/icons-material/KingBed';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';

interface Room {
    id: number;
    roomNumber: string;
    type: string;
    price: number;
    available: boolean;
}

interface RoomCardProps {
    room: Room;
    onBook: (roomId: number) => void;
    isBooking: boolean;
}

const RoomCard: React.FC<RoomCardProps> = ({ room, onBook, isBooking }) => {
    return (
        <div className="card flex items-center justify-between" style={{ marginBottom: '1rem', opacity: room.available ? 1 : 0.6 }}>
            <div className="flex items-center gap-4">
                <div style={{ 
                    background: 'var(--primary-hover)', 
                    color: 'white', 
                    padding: '1rem', 
                    borderRadius: '1rem',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center'
                }}>
                    <KingBedIcon />
                </div>
                <div>
                    <h4 style={{ fontSize: '1.1rem', marginBottom: '0.25rem' }}>
                        Room {room.roomNumber} - {room.type}
                    </h4>
                    <div className="flex items-center gap-1" style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>
                        <EventAvailableIcon style={{ fontSize: '1rem', color: room.available ? 'var(--primary)' : 'var(--secondary)' }} />
                        {room.available ? 'Available Now' : 'Currently Booked'}
                    </div>
                </div>
            </div>
            
            <div className="flex items-center gap-6">
                <div style={{ textAlign: 'right' }}>
                    <span style={{ fontSize: '1.25rem', fontWeight: 700, color: 'var(--primary)' }}>
                        ₹{room.price}
                    </span>
                    <span style={{ fontSize: '0.8rem', color: 'var(--text-muted)', display: 'block' }}>per night</span>
                </div>
                
                <button 
                    onClick={() => onBook(room.id)}
                    className="btn-primary"
                    disabled={!room.available || isBooking}
                    style={{ 
                        padding: '0.6rem 1.5rem', 
                        fontSize: '0.9rem',
                        opacity: room.available ? 1 : 0.5,
                        cursor: room.available ? 'pointer' : 'not-allowed'
                    }}
                >
                    {isBooking ? 'Booking...' : (room.available ? 'Book Room' : 'Sold Out')}
                </button>
            </div>
        </div>
    );
};

export default RoomCard;
