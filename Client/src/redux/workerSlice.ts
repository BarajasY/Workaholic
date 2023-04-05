import {createSlice} from '@reduxjs/toolkit';
import { workerStateInterface } from '../types';

export const WorkerInitialState = {
    FName: "",
    LName: "",
    Email: "",
    Password: "",
    Country: "",
    Tags: [""],
    Role: "",
    Logged: false
} as workerStateInterface

export const workerSlice = createSlice({
    name: "workerSlice",
    initialState: WorkerInitialState,
    reducers: {
        storeWorker: (state, action) => {
            const {FName, LName, Email, Country, Tags, Logged} = action.payload;
            state.FName = FName;
            state.LName = LName;
            state.Email = Email;
            state.Country = Country;
            state.Tags = Tags;
            state.Logged = Logged;
        },
        resetWorker: () => WorkerInitialState
    }
});

export const {storeWorker, resetWorker} = workerSlice.actions;
export default workerSlice.reducer;