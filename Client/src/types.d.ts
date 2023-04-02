export interface workerInterface {
    fname: String,
    lname: String,
    email: String,
    password: String,
    country: String,
    tags: String[]
}

export interface workerStateInterface {
    FName: String,
    LName: String,
    Email: String,
    Password: String,
    Country: String,
    Tags: String[],
    Logged: Boolean
}