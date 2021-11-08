import { useContext } from 'react';
import { useHistory } from 'react-router-dom';
import {AuthContext} from '../contexts/authentication';

const isAuth = (WrappedComponent) => {

    const Component = (props) => {
        const { currentUser } = useContext(AuthContext);
        const history = useHistory();

        if (!currentUser) {
            history.push('/login')

            return null;
        }

        return <WrappedComponent {...props} />
    }

    return Component;
};

export default isAuth;