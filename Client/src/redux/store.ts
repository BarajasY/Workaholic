import { configureStore } from "@reduxjs/toolkit";
import workerReducer from './workerSlice';
import companyReducer from './companySlice';

export const store = configureStore({
    reducer: {
        worker: workerReducer,
        company: companyReducer
    }
})

export default store;