import { configureStore } from "@reduxjs/toolkit";
import workerReducer from './workerSlice';

export const store = configureStore({
    reducer: {
        worker: workerReducer,
    }
})

export default store;