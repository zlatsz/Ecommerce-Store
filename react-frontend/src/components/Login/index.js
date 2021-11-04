import "./index.css";
import { useContext,useEffect} from 'react';
import { Link, useHistory } from 'react-router-dom';
import { AuthContext } from '../../contexts/authentication';
import firebase from '../../utils/firebase';
import Footer from "../Landing-page/Footer";

const Login = () => {
    const history = useHistory();

    const signIn = (e) => {
        e.preventDefault();

        const email = e.target.email.value;
        const password = e.target.password.value;

        firebase.auth()
            .signInWithEmailAndPassword(email, password)
            .catch((error) => alert(error.message));
    }

    const { currentUser } = useContext(AuthContext);

    useEffect(() => {
        if (currentUser) {
            history.push('/home');
        }
    },[currentUser, history] );

    return (
        <>
            <section className="login-page">
                <Link to="/" className="login-logo">
                    <img src="logo.png" alt="logo" />
                </Link>
                <article className="login-page-wrapper">
                    <h2>Влез в профила си:</h2>
                    <form className="login-page-content" onSubmit={signIn}>
                        <i className="fas fa-envelope fa-lg" aria-hidden="true"></i>
                        <input type="text" placeholder="Email" id="email" />

                        <i className="fa fa-lock fa-lg" aria-hidden="true"></i>
                        <input type="password" placeholder="Password" id="password" />

                        <button type="submit" className="login-page-submit" defaultValue="">Влез</button>
                    </form>
                    <h3>Регистрирай се <Link to="/register"><span>ТУК</span></Link></h3>
                </article>
            </section>
            <Footer />
        </>
    );
};


export default Login;