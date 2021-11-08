import './index.css';
import { NavLink } from 'react-router-dom';


const Footer = () => {
    return (
        <footer className="home-footer">
            <section className="home-footer-upper">
                <section className="home-footer-four">
                    <h2>За нас</h2>
                    <p>Магазин за артистични и ръчно изработени стоки.</p>
                    <p>Блог с информация за етеричните масла на doTerra.</p>
                </section>
                <section className="home-footer-one">
                    <h2>Продукти</h2>
                    <ul className="home-footer-one-sub">
                        <li className="home-footer-one-sub-item ">
                            <NavLink to="/products/jewellery">Бижута</NavLink>
                        </li>
                        <li className="home-footer-one-sub-item ">
                            <NavLink to="/products/cosmetics">Козметика</NavLink>
                        </li>
                        <li className="home-footer-one-sub-item ">
                            <NavLink to="/products/carts">Картички</NavLink>
                        </li>
                        <li className="home-footer-one-sub-item ">
                            <NavLink to="/products/more">Още...</NavLink>
                        </li>
                    </ul>
                </section>
                <section className="home-footer-two">
                    <h2>Sisters Art</h2>
                    <ul>
                        <li>
                            <NavLink to="/blog"> Блог </NavLink>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <NavLink to="/carts"> Кошница </NavLink>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <NavLink to="/contacts"> Условия </NavLink>
                        </li>
                    </ul>
                </section>
                <section className="home-footer-tree">
                    <h2>Доставка</h2>
                    <p>При поръчка над 80 лв. доставката е безплатна.</p>
                </section>
            </section>
            <section className="home-footer-bottom">
                <div className="home-footer-icons">
                    <a href="https://www.facebook.com/SistersArtOOD/" target="_blank" rel="noreferrer" className="home-footer-link">
                        <i className="fab fa-facebook-f">SistersArtStore</i></a>
                    <a href="https://www.facebook.com/groups/610448616214671/" target="_blank" rel="noreferrer" className="home-footer-link">
                        <i className="fab fa-facebook-f">EssentialOil</i></a>
                    <a href="https://www.doterra.com/" target="_blank" rel="noreferrer" className="home-footer-link">
                        <i className="fas fa-link">DoTerra</i></a>
                </div>
                <p>Copyright &copy; 2021 Sisters Art Store All Rights Reserved</p>
            </section>
        </footer>

    );
};

export default Footer;