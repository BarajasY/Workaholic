import { createSlice } from "@reduxjs/toolkit";

export const userInitialState = {
    id: 0,
    name: "",
    email: "",
    country: "",
    role : {
        id: 0,
        name: ""
    },
    logged: false
}

export const userSlice = createSlice({
    name: "userSlice",
    initialState: userInitialState,
    reducers : {
        storeUser: (state, action) => {
            const {id, name, email, country, role, logged} = action.payload;
            state.id = id,
            state.name = name,
            state.email = email,
            state.country = country,
            state.role = role,
            state.logged = logged
        },
        resetUser: () => userInitialState
    }
})

export const {storeUser, resetUser} = userSlice.actions
export default userSlice.reducer