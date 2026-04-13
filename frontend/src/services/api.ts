import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const hotelService = {
  searchHotels: (city: string, rating?: number) => 
    api.get('/hotels', { params: { city, minRating: rating } }),
  getHotel: (id: string) => api.get(`/hotels/${id}`),
};

export const roomService = {
  getRooms: (hotelId: string) => api.get(`/hotels/${hotelId}/rooms`),
};

export const bookingService = {
  createBooking: (userId: number, roomId: number, startDate: string, endDate: string) =>
    api.post('/bookings', { userId, roomId, startDate, endDate }),
  getUserBookings: (userId: number) => api.get(`/users/${userId}/bookings`),
};

export default api;
