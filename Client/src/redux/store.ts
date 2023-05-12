import { configureStore } from "@reduxjs/toolkit";
import postingReducer from './postingSlice'
import userReducer from './userSlice'

export const store = configureStore({
    reducer: {
        posting: postingReducer,
        user: userReducer
    }
})

export default store;