import { Post } from "./post";
import { ReactionType } from "./reaction-type";
import { User } from "./user";

export class Reaction {
    constructor(
        public id?: number,
        public content?: string,
        public reactionTimestamp?: Date,
        public isActive?: boolean,
        public userId?: number,
        public user?: User,
        public postId?: number,
        public post?: Post,
        public reactionTypeId?: number,
        public reactionType?: ReactionType

    ) {}
}
