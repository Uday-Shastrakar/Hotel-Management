import React from 'react';
import { useNavigate } from 'react-router-dom';
import StarIcon from '@mui/icons-material/Star';
import LocationOnIcon from '@mui/icons-material/LocationOn';

interface Hotel {
  id: number;
  name: string;
  city: string;
  rating: number;
  description: string;
  image?: string;
}

interface HotelCardProps {
  hotel: Hotel;
}

const HotelCard: React.FC<HotelCardProps> = ({ hotel }) => {
  const navigate = useNavigate();

  return (
    <div className="card flex-col" style={{ padding: 0, overflow: 'hidden', height: '100%' }}>
      <div style={{ 
        width: '100%', 
        height: '200px', 
        background: 'linear-gradient(45deg, #ddd, #eee)',
        position: 'relative'
      }}>
        {/* Placeholder for dynamic images */}
        <div style={{
          position: 'absolute',
          top: '1rem',
          right: '1rem',
          background: 'rgba(255, 255, 255, 0.9)',
          padding: '0.25rem 0.75rem',
          borderRadius: '2rem',
          display: 'flex',
          alignItems: 'center',
          gap: '0.25rem',
          fontWeight: 600,
          color: 'var(--accent)'
        }}>
          <StarIcon style={{ fontSize: '1rem' }} />
          {hotel.rating}
        </div>
      </div>
      <div style={{ padding: '1.5rem' }}>
        <h3 style={{ fontSize: '1.25rem', marginBottom: '0.5rem' }}>{hotel.name}</h3>
        <div className="flex items-center gap-1" style={{ color: 'var(--text-muted)', fontSize: '0.9rem', marginBottom: '1rem' }}>
          <LocationOnIcon style={{ fontSize: '1rem' }} />
          {hotel.city}
        </div>
        <p style={{ 
          fontSize: '0.9rem', 
          color: 'var(--text-muted)', 
          marginBottom: '1.5rem',
          display: '-webkit-box',
          WebkitLineClamp: 3,
          WebkitBoxOrient: 'vertical',
          overflow: 'hidden'
        }}>
          {hotel.description}
        </p>
        <button 
          onClick={() => navigate(`/hotel/${hotel.id}`)}
          className="btn-primary" 
          style={{ width: '100%' }}
        >
          View Details
        </button>
      </div>
    </div>
  );
};

export default HotelCard;
