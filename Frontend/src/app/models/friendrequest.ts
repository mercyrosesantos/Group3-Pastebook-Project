import { User } from "./user";

export class Friendrequest {
    constructor(
        public id?: number,
        public requestTimeStamp?: Date,
        public status?: string,
        public requestorId?: number,
        public requesteeId?: number,
        public requestor?: User,
        public requestee?: User
        ){}
}
