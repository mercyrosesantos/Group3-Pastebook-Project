import { Post } from "./post";
import { User } from "./user";

export class Notification {

    constructor(
        public id?: number,
        public notificationType?: string,
        public isRead?: boolean,
        public notificationTimestamp?: Date,
        public user?: User,
        public friend?: User,
        public post?: Post
    ) {}
    
}
