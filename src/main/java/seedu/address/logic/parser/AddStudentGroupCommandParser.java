package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddStudentGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupName;

/**
 * Parses input arguments and creates a new MarkStudentAttCommand object
 */
public class AddStudentGroupCommandParser implements Parser<AddStudentGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentGroupCommand
     * and returns a AddStudentGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddStudentGroupCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            GroupName group = ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_GROUP).get());
            return new AddStudentGroupCommand(index, group);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddStudentGroupCommand.MESSAGE_USAGE));
    }
}