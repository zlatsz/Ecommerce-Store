import './index.css';
import Header from './Header';
import Gallery from './Gallery';
import Blog from './Blog';
import Footer from './Footer';

const LandingPage = () => {
    return (
        <main className=".app">
            <Header />

            <Gallery />
            <Blog />
            <Footer />

        </main>

    );
};

export default LandingPage;