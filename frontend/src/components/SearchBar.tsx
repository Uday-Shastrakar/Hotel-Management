import React, { useState } from 'react';
import SearchIcon from '@mui/icons-material/Search';
import LocationCityIcon from '@mui/icons-material/LocationCity';
import StarRateIcon from '@mui/icons-material/StarRate';

interface SearchBarProps {
  onSearch: (city: string, rating?: number) => void;
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearch }) => {
  const [city, setCity] = useState('');
  const [rating, setRating] = useState<number | undefined>(undefined);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSearch(city, rating);
  };

  return (
    <div className="glass-panel" style={{ 
      padding: '2rem', 
      borderRadius: '2rem',
      maxWidth: '900px',
      margin: '2rem auto',
    }}>
      <form onSubmit={handleSubmit} className="flex items-center gap-4">
        <div style={{ flex: 1, position: 'relative' }}>
          <LocationCityIcon style={{ 
            position: 'absolute', 
            left: '1rem', 
            top: '50%', 
            transform: 'translateY(-50%)',
            color: 'var(--text-muted)'
          }} />
          <input
            type="text"
            placeholder="Where are you going?"
            className="input-field"
            value={city}
            onChange={(e) => setCity(e.target.value)}
            style={{
              width: '100%',
              padding: '1rem 1rem 1rem 3rem',
              borderRadius: '1rem',
              border: '1px solid var(--border)',
              background: 'var(--bg-main)',
              color: 'var(--text-main)',
              fontSize: '1rem'
            }}
          />
        </div>
        
        <div style={{ position: 'relative', width: '200px' }}>
          <StarRateIcon style={{ 
            position: 'absolute', 
            left: '1rem', 
            top: '50%', 
            transform: 'translateY(-50%)',
            color: 'var(--accent)'
          }} />
          <select
            className="input-field"
            value={rating || ''}
            onChange={(e) => setRating(e.target.value ? Number(e.target.value) : undefined)}
            style={{
              width: '100%',
              padding: '1rem 1rem 1rem 3rem',
              borderRadius: '1rem',
              border: '1px solid var(--border)',
              background: 'var(--bg-main)',
              color: 'var(--text-main)',
              fontSize: '1rem',
              appearance: 'none'
            }}
          >
            <option value="">Min Rating</option>
            <option value="3">3+ Stars</option>
            <option value="4">4+ Stars</option>
            <option value="4.5">4.5+ Stars</option>
          </select>
        </div>

        <button type="submit" className="btn-primary flex items-center gap-2">
          <SearchIcon />
          <span>Search</span>
        </button>
      </form>
    </div>
  );
};

export default SearchBar;
