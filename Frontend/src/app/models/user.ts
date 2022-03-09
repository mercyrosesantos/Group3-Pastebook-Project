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
        public mobileNumber?: number,
        public isActive?: boolean,
    ) {}
}
