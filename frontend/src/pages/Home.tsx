import React, { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { hotelService } from '../services/api';
import SearchBar from '../components/SearchBar';
import HotelCard from '../components/HotelCard';
import { CircularProgress } from '@mui/material';

const Home: React.FC = () => {
    const [searchParams, setSearchParams] = useState({ city: '', rating: undefined as number | undefined });

    const { data: hotels, isLoading, isError } = useQuery({
        queryKey: ['hotels', searchParams.city, searchParams.rating],
        queryFn: async () => {
            const response = await hotelService.searchHotels(searchParams.city, searchParams.rating);
            return response.data.content; // Spring Page object
        },
    });

    const handleSearch = (city: string, rating?: number) => {
        setSearchParams({ city, rating });
    };

    return (
        <div style={{ paddingBottom: '4rem' }}>
            <section style={{ textAlign: 'center', margin: '4rem 0' }}>
                <h1 style={{ fontSize: '3.5rem', marginBottom: '1rem' }}>
                    Find Your Next <span className="gradient-text">Adventure</span>
                </h1>
                <p style={{ fontSize: '1.2rem', color: 'var(--text-muted)', maxWidth: '600px', margin: '0 auto' }}>
                    Discover luxury hotels and cozy resorts with the best prices guaranteed.
                </p>
                <SearchBar onSearch={handleSearch} />
            </section>

            <section>
                <div className="flex items-center justify-between" style={{ marginBottom: '2rem' }}>
                    <h2 style={{ fontSize: '1.75rem' }}>
                        {searchParams.city ? `Hotels in ${searchParams.city}` : 'Featured Hotels'}
                    </h2>
                </div>

                {isLoading && (
                    <div className="flex items-center justify-center" style={{ minHeight: '300px' }}>
                        <CircularProgress style={{ color: 'var(--primary)' }} />
                    </div>
                )}

                {isError && (
                    <div className="card" style={{ textAlign: 'center', borderColor: 'var(--secondary)', color: 'var(--secondary)' }}>
                        <p>Failed to load hotels. Please try again later.</p>
                    </div>
                )}

                {!isLoading && !isError && (
                    <div style={{ 
                        display: 'grid', 
                        gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', 
                        gap: '2rem' 
                    }}>
                        {hotels?.map((hotel: any) => (
                            <HotelCard key={hotel.id} hotel={hotel} />
                        ))}
                        {hotels?.length === 0 && (
                            <div style={{ gridColumn: '1 / -1', textAlign: 'center', padding: '4rem' }}>
                                <p style={{ fontSize: '1.2rem', color: 'var(--text-muted)' }}>No hotels found for your search criteria.</p>
                            </div>
                        )}
                    </div>
                )}
            </section>
        </div>
    );
};

export default Home;
