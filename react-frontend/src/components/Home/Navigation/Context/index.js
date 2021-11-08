import { NavLink, useHistory } from 'react-router-dom';
import { useContext, useState, useEffect } from 'react';
// import * as ordersService from '../../../../services/ordersService';
import { AuthContext } from '../../../../contexts/authentication';
// import { CartContext } from '../../../../contexts/shoppingCart';


const Context = () => {
    let history = useHistory();
    const { currentUser } = useContext(AuthContext);
    // const { cart } = useContext(CartContext);
    // // const [order, setOrder] = useState({});

    // useEffect(() => {
    //     ordersService.getOne(cart.name)
    //         .then(res => setOrder(res))
    //         .catch((error) => alert(error.message));
    // }, [cart.name]);

    function logout() {
        // if (order) {
        //     if (!order.products) {
        //         ordersService.deleteCart(currentUser.token, cart.name)
        //             .catch((error) => {
        //                 alert(error.message)
        //             });
        //     }
        //     firebase.auth().signOut().then(() => {
        //         history.push('/')
        //     }).catch((error) => {
        //         alert(error.message)
        //     });
        // } else {
        //     firebase.auth().signOut().then(() => {
        //         history.push('/')
        //     }).catch((error) => {
        //         alert(error.message)
        //     });
        // }
    }

    return (
        <>
            <li className="header-navigation-bottom-right-profile">
                <NavLink to="/profile"> <i className="fas fa-user fa-lg" /></NavLink>
            </li>
            {/*<li className="header-navigation-bottom-right-profile">*/}
            {/*    <NavLink to={`/cart/${cart.name}`}> <i className="fas fa-shopping-cart"></i></NavLink>*/}
            {/*</li>*/}
            <li className="header-navigation-bottom-right-profile" onClick={logout}>
                <a> <i className="fas fa-sign-out-alt"></i> </a>
            </li>
        </>
    );
}

export default Context;