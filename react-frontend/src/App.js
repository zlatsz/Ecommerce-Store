import { Route, Switch } from 'react-router-dom';
// import { AuthProvider } from './contexts/authentication';
// import { CartProvider } from './contexts/shoppingCart';
// import isAuth from './hoc/isAuth';
import LandingPage from './components/Landing-page';
// import Contacts from "./components/Contacts";
import Login from './components/Login';
import Register from './components/Register';
// import Home from './components/Home';
// import AddProducts from "./components/AddProducts";
// import Profiles from "./components/Profiles";
// import AddPosts from "./components/AddPosts";
// import Products from "./components/Products/CategoryView";
// import ProductDetails from "./components/Product/ProductDetails";
// import EditProduct from "./components/Product/EditProduct";
// import Blog from "./components/Blog";
// import PostDetails from "./components/Post/PostDetails";
// import Cart from "./components/Cart/";
// import Profile from "./components/Login/Profile";
// import OldCart from "./components/Cart/OldCart";
// import Deliveries from "./components/Deliveries";
// import Messages from "./components/Contacts/Messages";
// import MyErrorBoundary from './components/ErrorBoundary';
import './App.css';

const App = () => {
  return (
      // <MyErrorBoundary>
      //   <AuthProvider>
          <Switch>
            <Route path="/" exact component={LandingPage} />
            <Route path="/login" component={Login} />
            <Route path="/register" component={Register} />
            {/*<Route path="/contacts" component={Contacts} />*/}
            {/*<Route path="/messages" exact component={isAuth(Messages)} />*/}
            {/*<CartProvider>*/}
            {/*  <Route path="/home" exact component={isAuth(Home)} />*/}
            {/*  <Route path="/add-products" exact component={isAuth(AddProducts)} />*/}
            {/*  <Route path="/profiles" exact component={isAuth(Profiles)} />*/}
            {/*  <Route path="/profile" exact component={isAuth(Profile)} />*/}
            {/*  <Route path="/add-posts" exact component={isAuth(AddPosts)} />*/}
            {/*  <Route path="/products/:category" component={isAuth(Products)} />*/}
            {/*  <Route path="/product/details/:productId" exact component={isAuth(ProductDetails)} />*/}
            {/*  <Route path="/product/edit/:productId" exact component={isAuth(EditProduct)} />*/}
            {/*  <Route path="/blog" exact component={isAuth(Blog)} />*/}
            {/*  <Route path="/blog/:postId" exact component={isAuth(PostDetails)} />*/}
            {/*  <Route path="/cart/:orderId" exact component={isAuth(Cart)} />*/}
            {/*  <Route path="/cart/old/:orderId" exact component={isAuth(OldCart)} />*/}
            {/*  <Route path="/deliveries" exact component={isAuth(Deliveries)} />*/}
            {/*</CartProvider>*/}
          </Switch>
      //   </AuthProvider>
      // </MyErrorBoundary>
  );
}

export default App;