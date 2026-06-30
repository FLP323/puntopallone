import { BrowserRouter, Routes, Route } from 'react-router-dom';
import TorneiPage from './pages/TorneiPage';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/tornei" element={<TorneiPage />} />
                <Route path="/" element={<div>Home - Puntopallone</div>} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;