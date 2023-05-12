import { createSlice } from "@reduxjs/toolkit";

export const postingInitialState = {
    id: 0,
    title : "",
    description: "",
    jobType: [{
        id:0,
        type: ""
    }],
    salary: 0,
    currency: {
        id: 0,
        code: ""
    },
    rate: {
        id: 0,
        rateName: ""
    },
    duration: "",
    date: "",
    benefits: [""],
    user : {
        id: 0,
        name: "",
        password: "",
        email: "",
        cvPath: "",
        country: {
            id: 0,
            name: ""
        },
        role: {
            id: 0,
            name: ""
        }
    }
}

export const postingSlice = createSlice({
    name: "postingSlice",
    initialState: postingInitialState,
    reducers : {
        storePosting: (state, action) => {
            const {title, id, company} = action.payload;
            state.title = title,
            state.id = id,
            state.user.id = company.id
        },
        resetPosting: () => postingInitialState
    }
})


export const {storePosting, resetPosting} = postingSlice.actions;
export default postingSlice.reducer;