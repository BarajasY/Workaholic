import { createSlice } from "@reduxjs/toolkit";

export const companyInitialState = {
    Name : "",
    Location: "",
    Password: "",
    Owner: "",
    Email: "",
    Country: "",
    Role: "",
    Tags: [""],
    Logged: false
}

export const companySlice = createSlice({
    name: "companySlice",
    initialState: companyInitialState,
    reducers: {
        storeCompany: (state, action) => {
            const {Name, Location, Owner, Email, Country, Role, Tags, Logged} = action.payload;
            state.Name = Name;
            state.Location = Location;
            state.Owner = Owner;
            state.Email = Email;
            state.Country = Country;
            state.Role = Role;
            state.Tags = Tags;
            state.Logged = Logged;
        },
        resetCompany: () => companyInitialState
    }
})

export const {storeCompany, resetCompany} = companySlice.actions;
export default companySlice.reducer;