import { Navigate, Outlet } from "react-router-dom"
import { useOktaAuth } from "@okta/okta-react";

const SecirRoute = () => {
    const { authState } = useOktaAuth();
    
   let auth = {'token': true}
   return(
    (authState && authState.isAuthenticated) ? <Outlet/>
    :
    <Navigate to='/login'/>
   ) 
}
export default SecirRoute