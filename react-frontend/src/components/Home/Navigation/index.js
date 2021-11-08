import './index.css';
import Header from './Header';
import Carousel from './Carousel';
import { useEffect, useState, useContext } from 'react';
import { AuthContext } from '../../../contexts/authentication';

const Navigation = () => {
    const { currentUser } = useContext(AuthContext);
    const [currentSlide, setCurrentSlide] = useState(0);
    const slides = [
        {
            id: 1,
            title: "First Slide",
            link: process.env.PUBLIC_URL + '/car-jew.jpg',
            path: '/products/jewelleries'
        },
        {
            id: 2,
            title: "Second Slide",
            link: process.env.PUBLIC_URL + '/set.jpg',
            path: '/blog'
        },
    ];
    const slideNext = (e) => {
        setCurrentSlide((prev) => {
            return prev + 1 === slides.length ? 0 : currentSlide + 1;
        });
    };
    const slidePrev = (e) => {
        setCurrentSlide((prev) => {
            return prev === 0 ? slides.length - 1 : currentSlide - 1;
        });
    };
    useEffect(() => {
        const intervalId = setInterval(() => {
            setCurrentSlide((prev) => {
                return prev + 1 === slides.length ? 0 : prev + 1;
            });
        }, 4000);
        return () => {
            clearInterval(intervalId);
        };
    }, [slides.length]);
    return (
        <>
        <header className="home-header">
            <Header />
            <h2>Здравей, {currentUser?.email}</h2>
            <Carousel
                image={slides[currentSlide]}
                slideNext={slideNext}
                slidePrev={slidePrev}
            />

        </header>
        </>
    );
};

export default Navigation;