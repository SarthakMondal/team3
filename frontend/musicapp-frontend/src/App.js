import 'bootstrap/dist/css/bootstrap.min.css';
import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";


import Header from './General/Header';
import Home from './General/Home';
import Footer from './General/Footer';

function App() {
  
  return (
    <>
    <Header />
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </BrowserRouter>
    <Footer />
    </>
  );
}

export default App;
