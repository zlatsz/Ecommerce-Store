import './index.css';
import { Link } from 'react-router-dom';
import { Component } from 'react';
import DataService from '../../../utils/request-service';

class Gallery extends Component {
    // constructor(props){
    //     super(props)
    //     this.state = {
    //         categories:[]
    //     }
    // }
    //
    // componentDidMount(){
    //     DataService.getCategories().then((response) => {
    //         this.setState({ categories: response.data})
    //     });
    // }


    render() {
        return (
            <>
                {/*{*/}
                {/*    this.state.categories.map(*/}
                {/*        category =>*/}
                {/*        <div>{category.name}</div>*/}
                {/*    )*/}
                {/*}*/}
                <h2 className="landing-gallery-title">Галерия</h2>
                <section className="landing-gallery">
                    <article>
                        <img src={process.env.PUBLIC_URL + '/FB_IMG_1595763576797.jpg'} alt="jewellery" />
                        <Link to="/login" className="landing-gallery-link">Виж повече ..</Link>
                    </article>
                    <article>
                        <img src={process.env.PUBLIC_URL + '/FB_IMG_1595763602862.jpg'} alt="jewellery" />

                        <Link to="/login" className="landing-gallery-link">Виж повече ..</Link>
                    </article>
                    <article>
                    <img src={process.env.PUBLIC_URL + '/FB_IMG_1595763613765.jpg'} alt="jewellery" />
                        <Link to="/login" className="landing-gallery-link">Виж повече ..</Link>
                    </article>
                    <article>
                    <img src={process.env.PUBLIC_URL + '/FB_IMG_1595863001917.jpg'} alt="card" />
                        <Link to="/login" className="landing-gallery-link">Виж повече ..</Link>
                    </article>
                    <article>
                    <img src={process.env.PUBLIC_URL + '/FB_IMG_1595763555617.jpg'} alt="jewellery" />
                        <Link to="/login" className="landing-gallery-link">Виж повече ..</Link>
                    </article>
                </section>
            </>
        );
    };
}

export default Gallery;