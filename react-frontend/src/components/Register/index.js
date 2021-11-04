import "./index.css";
import { useContext, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { AuthContext } from '../../contexts/authentication';
import firebase from '../../utils/firebase';
import Footer from "../Landing-page/Footer";

const Register = () => {
    const history = useHistory();

    const signUp = (e) => {
        e.preventDefault()
        const email = e.target.email.value;
        const password = e.target.password.value;
        const repeatPassword = e.target.repeatPassword.value;
        if(repeatPassword!==password){
            alert("Password didn't match");
            return;
        }
        firebase.auth()
            .createUserWithEmailAndPassword(email, password)
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
                <section className="register-page">
                    <Link to="/" className="register-logo">
                        <img src="logo.png" alt="logo" />
                    </Link>
                    <article className="register-page-wrapper">
                        <h2>Регистрирай се:</h2>
                        <form className="register-page-content" onSubmit={signUp}>
                            <i className="fas fa-envelope fa-lg" aria-hidden="true"></i>
                            <input type="text" placeholder="Email" id="email"/>

                            <i className="fa fa-lock fa-lg" aria-hidden="true"></i>
                            <input type="password" placeholder="Password" id="password"  />

                            <i className="fa fa-unlock fa-lg" aria-hidden="true"></i>
                            <input type="password" placeholder="Repeat Password" id="repeatPassword" />

                            <button type="submit" className="register-page-submit" value="">Влез</button>
                        </form>
                        <h3>Ако си регистриран влез от <Link to="/login"><span>ТУК</span></Link></h3>
                    </article>
                </section>
                <Footer />
            </>
        );
    };

export default Register;