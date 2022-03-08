import { User } from "./user";

export class Post {
    constructor(
        public id?: number,
        public content?: string,
        public postTimestamp?: string,
        public isActive?: boolean,
        public userId?: number,
        public user?: User,
        public timelineUserId?: number
    ) {}
}
