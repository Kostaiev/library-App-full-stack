import { Navigate, Outlet } from "react-router-dom";
import { useOktaAuth } from "@okta/okta-react";

export const SecurRoute = () => {
  const { authState } = useOktaAuth();
  console.log(authState, authState?.isAuthenticated);
  const isAuthenticated = authState?.isAuthenticated;

  return true ? <Outlet /> : <Navigate to="/login" />;
};
