export interface workerInterface {
    fname: String,
    lname: String,
    email: String,
    password: String,
    country: String,
    role: String,
    tags: String[]
}

export interface companyInterface {
    name: String,
    owner: String,
    password: String,
    role: String,
    location: String,
    email: String,
    country: String,
    tags: String[]
}

export interface companyStateInterface {
    Name: String,
    Owner: String,
    Password: String,
    Role: String,
    Location: String,
    Email: String,
    Country: String,
    Tags: String[],
    Logged: Boolean
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

export type WorkerType = {
    worker: WorkerStateInterface
}