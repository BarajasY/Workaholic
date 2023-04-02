import {createSlice} from '@reduxjs/toolkit';
import { workerStateInterface } from '../types';

const initialState = {
    FName: "",
    LName: "",
    Email: "",
    Password: "",
    Country: "",
    Tags: [""],
    Logged: false
} as workerStateInterface

export const workerSlice = createSlice({
    name: "workerSlice",
    initialState,
    reducers: {
        storeWorker: (state, action) => {
            const {FName, LName, Email, Country, Tags, Logged} = action.payload;
            state.FName = FName;
            state.LName = LName;
            state.Email = Email;
            state.Country = Country;
            state.Tags = Tags;
            state.Logged = Logged;
        }
    }
})

export const {storeWorker} = workerSlice.actions;
export default workerSlice.reducer;