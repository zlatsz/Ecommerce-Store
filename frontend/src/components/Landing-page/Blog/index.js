import './index.css';
import { Link } from 'react-router-dom';

const Blog = () => {
    return (
        <section className="landing-blog">
            <div className="landing-blog-title">
                <h4>ЕТЕРИЧНИ МАСЛА<span>БЛОГ</span></h4>
                <Link to="/login" className='landing-blog-link'>ОТИДИ ТАМ {'>'}</Link>
            </div>
            <article className='landing-blog-wrapper'>
                <div className="landing-about-blog">
                    <h3>ЕТЕРИЧНИТЕ МАСЛА И ТЯХНОТО ВЛИЯНИЕ ЗА ЖИВОТ БЕЗ БОЛЕСТ</h3>
                    <p>Хармонията между духа и тялото е най-доброто, което можем да пожелаем за себе си и близките си.
                       Етеричните масла се използват от древни времена не само заради аромата, но и въздействието им върху ума, 
                       тялото и емоциите. Доказано е, че имат способността да намаляват болката и възпаленията, 
                       да облекчават депресивни състояния и да подобряват паметта.
                       В този блог ще се постараем да ви предоставим систематизирана информация за приложението 
                       на етеричните масла на doTerra.</p>
                    <Link to="/login"><button>Прочети повече...</button></Link>
                </div>
                <img src={process.env.PUBLIC_URL + '/set.jpg'} alt="oil" />
            </article>
        </section>
    );
};

export default Blog;