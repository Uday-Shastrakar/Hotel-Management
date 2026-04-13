import React from 'react';
import { Link } from 'react-router-dom';
import TravelExploreIcon from '@mui/icons-material/TravelExplore';
import PersonIcon from '@mui/icons-material/Person';

const Navbar: React.FC = () => {
  return (
    <nav className="glass-panel" style={{ 
      position: 'sticky', 
      top: 0, 
      zIndex: 100, 
      margin: '1rem', 
      borderRadius: '1rem',
      padding: '0.75rem 2rem'
    }}>
      <div className="flex items-center justify-between">
        <Link to="/" className="flex items-center gap-2">
          <TravelExploreIcon style={{ color: 'var(--primary)', fontSize: '2rem' }} />
          <span className="gradient-text" style={{ fontSize: '1.5rem', fontWeight: 700 }}>
            Grand Palace Hotels
          </span>
        </Link>
        <div className="flex items-center gap-4">
          <Link to="/" style={{ fontWeight: 500 }}>Find Hotels</Link>
          <Link to="/my-bookings" style={{ fontWeight: 500 }}>My Bookings</Link>
          <div className="flex items-center gap-2" style={{ 
            background: 'var(--border)', 
            padding: '0.5rem 1rem', 
            borderRadius: '2rem'
          }}>
            <PersonIcon style={{ fontSize: '1.2rem' }} />
            <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>Guest User</span>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
