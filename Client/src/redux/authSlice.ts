import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    loggedIn: false,
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        storeAuth: (state, action) => {
            const {loggedIn} = action.payload;
            state.loggedIn = loggedIn;
        }
    }
})

export const {storeAuth} = authSlice.actions;
export default authSlice.reducer;