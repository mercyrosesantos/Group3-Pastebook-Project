import { Photo } from "./photo";

export class User {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public password?: string,
        public birthDay?: Date,
        public dateJoined?: Date,
        public gender?: string,
        public mobileNumber?: string,
        public isActive?: boolean,
        public photoId?: number,
        public photo?: Photo

    ) {}
}
