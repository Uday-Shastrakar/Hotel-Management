import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import HotelDetails from './pages/HotelDetails';
import MyBookings from './pages/MyBookings';

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <Router>
        <div className="app-container">
          <Navbar />
          <main className="container">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/hotel/:id" element={<HotelDetails />} />
              <Route path="/my-bookings" element={<MyBookings />} />
            </Routes>
          </main>
          
          <footer style={{ 
            marginTop: '4rem', 
            padding: '2rem', 
            textAlign: 'center', 
            borderTop: '1px solid var(--border)',
            color: 'var(--text-muted)'
          }}>
            <p>&copy; 2026 Grand Palace Hotels. All rights reserved.</p>
          </footer>
        </div>
      </Router>
    </QueryClientProvider>
  );
}

export default App;
