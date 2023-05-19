export interface workerInterface {
    id: Number,
    fname: String,
    lname: String,
    email: String,
    password: String,
    country: String,
    role: String,
    tags: String[]
}

export interface companyInterface {
    id: Number,
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
    Id: Number,
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
    Id: Number,
    FName: String,
    LName: String,
    Email: String,
    Password: String,
    Country: String,
    Tags: String[],
    Logged: Boolean
}

export interface userStateInterface {
    id: Number,
    name: String,
    email: String,
    country: CountryType,
    role: RoleType,
    logged: Boolean,
    password: String
}

export type RoleType = {
    id: Number,
    name: String
}

export type WorkerType = {
    worker: WorkerStateInterface
}

export type CompanyType = {
    company: CompanyStateInterface
}

export type userType = {
    user: userStateInterface
}

export type tagType = {
    id: number,
    tagName: string,
    users: []
}

export type PostingType = {
    id: number,
    title: string,
    description: string,
    jobTypes: jobTypeType[],
    salary: number,
    currency: currencyType,
    rate: rateType,
    duration: number,
    date: String,
    benefits: string,
    user: userStateInterface
}

export type CountryType = {
    id: number,
    name: string
}

export type jobTypeType = {
    id: number,
    type: string
}

export type currencyType = {
    id: number,
    code: string
}

export type rateType = {
    id: number,
    rateName: string
}