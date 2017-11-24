package johnson.steam.usefull.api

import com.fasterxml.jackson.annotation.JsonAutoDetect

/** @author karpov-em on 24.11.2017*/
@JsonAutoDetect(
        creatorVisibility = JsonAutoDetect.Visibility.NONE,
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
interface IResponse