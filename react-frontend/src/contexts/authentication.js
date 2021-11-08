import React, { useEffect, useState } from "react";
import * as adminService from "../services/userService";

export const AuthContext = React.createContext({ currentUser: null });

export const AuthProvider = ({ children }) => {

  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
      const user = JSON.parse(localStorage.getItem('user'));
      if (!user) {
          setCurrentUser(null);
      } else if (user && user.accessToken){
          const isAdmin = adminService.adminCheck(user);
          user['isAdmin'] = isAdmin;
      }
      setCurrentUser(user);

  }, []);



  return (
    <AuthContext.Provider 
    value={{currentUser}}
    >
      {children}
    </AuthContext.Provider>
  );
};