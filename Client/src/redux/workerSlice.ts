import {createSlice} from '@reduxjs/toolkit';
import { userStateInterface, workerStateInterface } from '../types';

export const userInitialState = {
    FName: "",
    CompanyName: "",
    LName: "",
    Email: "",
    Password: "",
    Country: "",
    Tags: [""],
    Role: "",
    Logged: false,
    CompanyOwner: "",
    CompanyLocation: "",
} as userStateInterface

export const workerSlice = createSlice({
    name: "workerSlice",
    initialState: userInitialState,
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
        storeCompany: (state, action) => {
            const {CompanyName, CompanyLocation, CompanyOwner, Email, Role, Country, Tags, Logged} = action.payload;
            state.CompanyName = CompanyName;
            state.CompanyLocation = CompanyLocation;
            state.CompanyOwner = CompanyOwner;
            state.Email = Email;
            state.Role = Role;
            state.Country = Country;
            state.Tags = Tags;
            state.Logged = Logged;
        },
        resetUser: () => userInitialState,
    }
});

export const {storeWorker, resetUser, storeCompany} = workerSlice.actions;
export default workerSlice.reducer;