import { configureStore } from "@reduxjs/toolkit";
import workerReducer from './workerSlice';
import companyReducer from './companySlice';
import postingReducer from './PostingSlice'

export const store = configureStore({
    reducer: {
        worker: workerReducer,
        company: companyReducer,
        posting: postingReducer
    }
})

export default store;