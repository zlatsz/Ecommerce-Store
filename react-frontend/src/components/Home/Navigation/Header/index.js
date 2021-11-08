import "./index.css"
import { Link, NavLink, withRouter } from 'react-router-dom';
import { Component } from 'react';
import { AuthContext } from '../../../../contexts/authentication';
import Context from "../Context";
import Search from "../Search";


class Header extends Component {

    static contextType = AuthContext;

    render() {
        const currentUser = this.context;
        let userProperty = Object.values(currentUser);
        return (
            <section className="header-navigation-wrapper">
                <section className="header-navigation-upper">
                    <h3>Sisters Art Store</h3>
                    <Link to="/home" className="header-home-logo">
                        <img src={process.env.PUBLIC_URL + '/logo.png'} alt="logo" />
                    </Link>
                    <Search />
                </section>
                <nav className="header-navigation-bottom">
                    <ul className="header-navigation-bottom-left">
                        <li className="header-navigation-bottom-left-item">
                            <a className="header-navigation-bottom-left-title"> Продукти </a>
                            <ul className="header-navigation-bottom-left-sub">
                                <li className="header-navigation-bottom-left-sub-item ">
                                    <NavLink to="/products/jewelleries">Бижута</NavLink>
                                </li>
                                <li className="header-navigation-bottom-left-sub-item ">
                                    <NavLink to="/products/cosmetics">Козметика</NavLink>
                                </li>
                                <li className="header-navigation-bottom-left-sub-item ">
                                    <NavLink to="/products/carts">Картички</NavLink>
                                </li>
                                <li className="header-navigation-bottom-left-sub-item ">
                                    <NavLink to="/products/others">Още...</NavLink>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <NavLink to="/contacts"> За нас </NavLink>
                        </li>
                        <li>
                            <NavLink to="/blog"> Блог </NavLink>
                        </li>
                    </ul>
                    { userProperty[0].isAdmin &&
                        <ul className="header-navigation-bottom-middle">
                            <li className="header-navigation-bottom-middle-products">
                                <a> Добави <i className="fa fa-caret-down" aria-hidden="true"></i></a>
                                <ul className="header-navigation-bottom-middle-sub">
                                    <li className="header-navigation-bottom-middle-sub-item ">
                                        <NavLink to="/add-products">Добави продукт</NavLink>
                                    </li>
                                    <li className="header-navigation-bottom-middle-sub-item ">
                                        <NavLink to="/add-posts">Добави статия</NavLink>
                                    </li>
                                </ul>
                            </li>
                            <li className="header-navigation-bottom-middle-profiles">
                                <NavLink to="/profiles"> Поръчки </NavLink>
                            </li>
                            <li className="header-navigation-bottom-middle-profiles">
                                <NavLink to="/messages"> Съобщения </NavLink>
                            </li>
                        </ul>
                    }
                    <ul className="header-navigation-bottom-right">
                        <Context/>
                    </ul>
                </nav>


            </section>
        );
    };
};

export default withRouter(Header);