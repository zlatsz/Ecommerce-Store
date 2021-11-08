// import * as productsService from '../../../../services/productsService';
import { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

const Search = () => {
    const history = useHistory();

    // const [products, setProducts] = useState({});
    // let allProducts = [];

    // useEffect(() => {
    //     productsService.getAll()
    //     .then(res => setProducts(res))
    //     .catch((error) => alert(error.message));
    // }, []);
    //
    // let result = Array.from(products);
    // allProducts = result.map((product) => { return { ...product } });

    const search = (e) => {
        e.preventDefault();
        //
        // const productName = e.target.search.value;
        // allProducts = allProducts.filter((product) =>
        //     product.name.toLowerCase().includes(productName));
        //
        // if (allProducts.length>0) {
        //     let id = allProducts[0].id;
        //     history.push(`/product/details/${id}`);
        // } else {
        //     history.push("/home");
        // }
    }

    return (
        <form className="home-search" onSubmit={search}>
            <i className="fas fa-phone-volume">0888808808</i>
            <input type="text" id="search" />
            <button type="submit" className="home-search-btn"><i className="fa fa-search fa-lg"></i></button>
        </form >
    );
}

export default Search;



