import { Photo } from "./photo";
import { User } from "./user";

export class Album {
    constructor(
        public id?: number,
        public albumName?: string,
        public albumTimestamp?: Date,
        // public isActive?: boolean,
        // public userId?: number,
        // public user?: User,
        public albumPhotos?: Photo[],
        public userIdJson?: number,
        // public photoId?: number,
    ) {}
}
