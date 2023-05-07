import { configureStore } from "@reduxjs/toolkit";
import workerReducer from './workerSlice';
import companyReducer from './companySlice';
import postingReducer from './postingSlice'
import userReducer from './userSlice'

export const store = configureStore({
    reducer: {
        worker: workerReducer,
        company: companyReducer,
        posting: postingReducer,
        user: userReducer
    }
})

export default store;