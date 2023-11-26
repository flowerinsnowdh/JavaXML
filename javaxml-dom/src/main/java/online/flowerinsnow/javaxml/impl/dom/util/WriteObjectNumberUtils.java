package online.flowerinsnow.javaxml.impl.dom.util;

import online.flowerinsnow.javaxml.api.annotation.MissDo;
import online.flowerinsnow.javaxml.api.annotation.WrongTypeDo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class WriteObjectNumberUtils {
    public static <T> void getConfigValue(@Nullable String value, @NotNull MissDo missDo, @NotNull WrongTypeDo wrongTypeDo, @Nullable String defaultValue, @Nullable Function<String, T> parseValueAction, @Nullable Consumer<T> successAction, @Nullable Consumer<Exception> wrongTypeThrowingAction, @Nullable Runnable missingThrowingAction, @Nullable Consumer<T> defaultAction, @Nullable Consumer<Exception> defaultExceptionAction) {
        Objects.requireNonNull(missDo);
        Objects.requireNonNull(wrongTypeDo);
        Objects.requireNonNull(parseValueAction);

        if (value == null) {
            switch (missDo) {
                case DEFAULT:
                    try {
                        T finalValue = parseValueAction.apply(defaultValue);
                        if (defaultAction != null) {
                            defaultAction.accept(finalValue);
                        }
                    } catch (Exception e) {
                        if (defaultExceptionAction != null) {
                            defaultExceptionAction.accept(e);
                        }
                    }
                    break;
                case THROWING:
                    if (missingThrowingAction != null) {
                        missingThrowingAction.run();
                    }
                    break;
            }
        } else {
            try {
                T finalValue = parseValueAction.apply(defaultValue);
                if (successAction != null) {
                    successAction.accept(finalValue);
                }
            } catch (Exception e) {
                switch (wrongTypeDo) {
                    case DEFAULT:
                        try {
                            T finalValue = parseValueAction.apply(defaultValue);
                            if (defaultAction != null) {
                                defaultAction.accept(finalValue);
                            }
                        } catch (Exception ex) {
                            if (defaultExceptionAction != null) {
                                defaultExceptionAction.accept(ex);
                            }
                        }
                        break;
                    case THROWING:
                        if (wrongTypeThrowingAction != null) {
                            wrongTypeThrowingAction.accept(e);
                        }
                        break;
                }
            }
        }
    }
}
