import { createSlice } from "@reduxjs/toolkit";

export const postingInitialState = {
    id: 0,
    businessName: "",
    title : "",
    description: "",
    jobType: [""],
    salary: 0,
    salaryCurrency: "",
    salaryRate: "",
    location: "",
    country: "",
    duration: "",
    date: "",
    tags: [""],
    benefits: [""],
    company : {
        id: 0,
        name: "",
        location: "",
        password: "",
        owner: "",
        email: "",
        country: "",
        tags: "",
        role: ""
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
            state.company.id = company.id
        },
        resetPosting: () => postingInitialState
    }
})


export const {storePosting, resetPosting} = postingSlice.actions;
export default postingSlice.reducer;