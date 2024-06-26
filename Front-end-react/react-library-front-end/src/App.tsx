import React from "react";
import "./App.css";
import { useNavigate } from "react-router-dom";
import { Navbar } from "./layouts/NavbarAndFooter/Navbar";
import { Footer } from "./layouts/NavbarAndFooter/Footer";
import { HomePage } from "./layouts/HomePage/HomePage";
import { SearchBooksPage } from "./layouts/SearchBooksPage/SearchBooksPage";
import { Navigate, Route, Routes } from "react-router-dom";
import { BookCheckoutPage } from "./layouts/BookCheckoutPage/BookCheckoutPage";
import { oktaConfig } from "./lib/oktaConfig";
import { OktaAuth, toRelativeUrl } from "@okta/okta-auth-js";
import { Security, LoginCallback, useOktaAuth } from "@okta/okta-react";
import LoginWidget from "./Auth/LoginWidget";
import { ReviewListPage } from "./layouts/BookCheckoutPage/ReviewListPage/ReviewListPage";
import { ShelfPage } from "./layouts/ShelfPage/ShelfPage";
import {SecurRoute}  from "./SecurRoute";
import { MessagesPage } from "./layouts/MessagesPage/MessagesPage";


const oktaAuth = new OktaAuth(oktaConfig);
export const App = () => {
  const history = useNavigate();
  const customAuthHandler = () => {
    history("/login");
  };
  
   const authState  = useOktaAuth();


  const restoreOriginalUri = async (_oktaAuth: any, originalUri: any) => {
    history(toRelativeUrl(originalUri || "/", window.location.origin), {
      replace: true,
    });
  };
  
  return (
    <div className="d-flex flex-column min-vh-100">
      <Security
        oktaAuth={oktaAuth}
        restoreOriginalUri={restoreOriginalUri}
        onAuthRequired={customAuthHandler}
      >
        <Navbar />
        <div className="flex-grow-1">
          <Routes>
            <Route path="/" element={<Navigate to="/home" replace />}></Route>
            <Route path="/home" element={<HomePage />}></Route>
            <Route path="/search" element={<SearchBooksPage />}></Route>
            <Route path="/reviewlist/:bookId" element={<ReviewListPage />}></Route>
            <Route
              path="/checkout/:bookId"
              element={<BookCheckoutPage />}
            ></Route>
            <Route
              path="/login"
              element={<LoginWidget config={oktaConfig} />}
            ></Route>
            <Route path="/login/collback" element={<LoginCallback />}></Route>
            <Route element={<SecurRoute />}>
              <Route path="/messages" element={<MessagesPage/>} />
              <Route path="/shelf" element={<ShelfPage/>} />
            </Route>
            
          </Routes>
        </div>
        <Footer />
      </Security>
    </div>
  );
};
