import { User } from "./user";

export class Post {
    constructor(
        public id?: number,
        public content?: string,
        public postTimestamp?: Date,
        public isActive?: boolean,
        public user?: User,
        public timelineUser?: User
    ) {}
}
