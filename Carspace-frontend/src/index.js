import React from 'react';
import ReactDOM from 'react-dom/client';
import "bootstrap/dist/css/bootstrap.min.css";
// Bootstrap Bundle JS
import "bootstrap/dist/js/bootstrap.bundle.min";
import 'jquery';

import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { CookiesProvider } from 'react-cookie';
import { UserProvider } from './UserProvider';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <CookiesProvider>
      <UserProvider>
      <App />
      </UserProvider>
    </CookiesProvider>
);


reportWebVitals();
