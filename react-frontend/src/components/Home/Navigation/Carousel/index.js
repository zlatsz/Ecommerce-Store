import "./index.css"
import { NavLink } from 'react-router-dom';

const Carousel = (props) => {

    return (
        <section className="home-header-carousel">
            <NavLink to={props.image.path}><img className="home-header-carousel-img" src={props.image.link} alt="Slider_image" /></NavLink>
            <article className="home-header-carousel-btn-wrapper">
                <button onClick={props.slidePrev}>
                <i className="fas fa-caret-left fa-2x"></i>
                </button>
                <button onClick={props.slideNext}>
                <i className="fas fa-caret-right fa-2x"></i>
                </button>

            </article>
        </section>
    );
};

export default Carousel;