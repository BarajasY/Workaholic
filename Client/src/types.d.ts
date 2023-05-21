export interface userStateInterface {
    id: Number,
    name: String,
    email: string,
    country: CountryType,
    role: RoleType,
    logged: Boolean,
    password: String
}

export type RoleType = {
    id: Number,
    name: String
}

export type userType = {
    user: userStateInterface
}

export type PostingType = {
    posting: PostingInterface
}

export type tagType = {
    id: number,
    tagName: string,
    users: []
}

export interface PostingInterface {
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

export interface jobApplicationInterface {
    coverLetter: string,
    user: userStateInterface, 
    posting: PostingInterface
}